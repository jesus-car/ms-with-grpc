package com.msgrpc.restaurantms.service;

import com.msgrpc.restaurantms.ProductRequest;
import com.msgrpc.restaurantms.ProductResponse;
import com.msgrpc.restaurantms.ProductServiceGrpc;
import com.msgrpc.restaurantms.model.ProductDTO;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Service
public class RestaurantService {

    @GrpcClient("product-ms")
    private ProductServiceGrpc.ProductServiceStub productService;

    public void getProducts(Long restaurantId, SseEmitter emitter) {

        ProductRequest productRequest = ProductRequest.newBuilder().setRestaurantId(restaurantId).build();

        productService.getProductsByRestaurant(productRequest, new StreamObserver<ProductResponse>() {
            @Override
            public void onNext(ProductResponse productResponse) {
                ProductDTO productDTO = new ProductDTO(productResponse);
                try {
                    emitter.send(productDTO, MediaType.APPLICATION_JSON);
                } catch (IOException e) {
                    emitter.completeWithError(e);
                }
            }

            @Override
            public void onError(Throwable throwable) {
                emitter.completeWithError(throwable);
            }

            @Override
            public void onCompleted() {
                emitter.complete();
            }
        });

    }
}
