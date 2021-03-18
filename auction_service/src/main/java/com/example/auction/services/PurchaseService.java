package com.example.auction.services;

import com.example.auction.model.dao.AuctionEntity;
import com.example.auction.model.dao.PurchaseEntity;
import com.example.auction.model.dao.UserEntity;
import com.example.auction.model.dto.AddPurchaseResponse;
import com.example.auction.model.dto.GetPurchaseResponse;
import com.example.auction.repositories.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public PurchaseEntity createPurchaseEntity (UserEntity userEntity, AuctionEntity auctionEntity){
        PurchaseEntity purchaseEntity = new PurchaseEntity();
        purchaseEntity.setAuctionEntity(auctionEntity);
        purchaseEntity.setUserEntity(userEntity);
        purchaseEntity.setPrice(auctionEntity.getBuyNowPrice());
        purchaseEntity.setDate(LocalDateTime.now());
        return purchaseEntity;
    }
    public void savePurchaseInDB (PurchaseEntity purchaseEntity){
        purchaseRepository.save(purchaseEntity);
    }
    public AddPurchaseResponse mapPurchaseEntityToAddPurchaseResponse (PurchaseEntity purchaseEntity){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(purchaseEntity, AddPurchaseResponse.class);
    }
    public List<PurchaseEntity> getAllPurchaseByUser (UserEntity userEntity){
        return purchaseRepository.findAllByUserEntity(userEntity);
    }

}
