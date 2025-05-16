package com.msgrpc.productms.service;

import com.msgrpc.productms.repository.ProductRepository;
import com.msgrpc.restaurantms.ProductRequest;
import com.msgrpc.restaurantms.ProductResponse;
import com.msgrpc.restaurantms.ProductServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class ProductService extends ProductServiceGrpc.ProductServiceImplBase {

    private final ProductRepository productRepository;

    @Override
    public void getProductsByRestaurant(ProductRequest request, StreamObserver<ProductResponse> responseObserver) {
        productRepository.findAllByRestaurantId(request.getRestaurantId()).stream()
                .map(product -> ProductResponse.newBuilder()
                        .setName(product.getName())
                        .setDescription(product.getDescription())
                        .setPrice(product.getPrice())
                        .setStock(product.getStock())
                        .build())
                .forEach(responseObserver::onNext);

        responseObserver.onCompleted();
    }
}
