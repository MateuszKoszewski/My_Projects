package com.example.auction.services;

import com.example.auction.model.dao.AuctionEntity;
import com.example.auction.model.dao.LicytationEntity;
import com.example.auction.model.dao.UserEntity;
import com.example.auction.model.dto.AddLicytationResponse;
import com.example.auction.model.dto.GetLicytationsResponse;
import com.example.auction.model.dto.GetObservationOfAuctionResponse;
import com.example.auction.repositories.LicytationsRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LicytationService {

    private final LicytationsRepository licytationsRepository;

    private List<LicytationEntity> getListOfLicytationEntityByAuction(AuctionEntity auctionEntity) {
        return licytationsRepository.findAllByAuction(auctionEntity);
    }
//    private GetLicytationsResponse mapLicytationEntityToGetLicytationsResponse (LicytationEntity licytationEntity){
//        ModelMapper mapper = new ModelMapper();
//        return mapper.map(licytationEntity, GetLicytationsResponse.class);
//    }
//
//    private List<GetLicytationsResponse> mapListOfLicytationEntityToListOfGetLicytationsResponse (List<LicytationEntity> listOfLicytationEntity){
//        return listOfLicytationEntity.stream().map(this::mapLicytationEntityToGetLicytationsResponse).collect(Collectors.toList());
//    }
//
//    public List<GetLicytationsResponse> getListOfGetLicytationsResponseByAuction (AuctionEntity auctionEntity){
//        List<LicytationEntity> listOfLicytationEntityByAuction = getListOfLicytationEntityByAuction(auctionEntity);
//        return mapListOfLicytationEntityToListOfGetLicytationsResponse(listOfLicytationEntityByAuction);
//    }

    public List<GetLicytationsResponse> getListOfGetLicytationsResponseByAuction(AuctionEntity auctionEntity) {
        ModelMapper mapper = new ModelMapper();
        List<LicytationEntity> listOfLicytationEntityByAuction = getListOfLicytationEntityByAuction(auctionEntity);
        return listOfLicytationEntityByAuction.stream().map(licytationEntity -> mapper.map(licytationEntity, GetLicytationsResponse.class)).collect(Collectors.toList());
    }

    public double getMaxValueFromLicytations(AuctionEntity auctionEntity) {
        List<LicytationEntity> allLicytations = licytationsRepository.findAllByAuction(auctionEntity);
        double maxValue = auctionEntity.getMinPrice();
        for (LicytationEntity allLicytation : allLicytations) {
            if (allLicytation.getPrice() > maxValue) {
                maxValue = allLicytation.getPrice();
            }
        }
        return maxValue;
    }

    public LicytationEntity createLicytationEntity(AuctionEntity auctionEntity, UserEntity userEntity, double price) {
        LicytationEntity licytation = new LicytationEntity();
        licytation.setAuction(auctionEntity);
        licytation.setUser(userEntity);
        licytation.setPrice(price);
        return licytation;
    }

    public void saveLicytationToDB(LicytationEntity licytationEntity) {
        licytationsRepository.save(licytationEntity);
    }
    public LicytationEntity getLastLicytationOfAuction (AuctionEntity auctionEntity){
        List<LicytationEntity> listOfLicytations = getListOfLicytationEntityByAuction(auctionEntity);
        if (listOfLicytations.size()==0){
            return null;
        }
        else {
            return listOfLicytations.get(listOfLicytations.size()-1);
        }
    }
}
