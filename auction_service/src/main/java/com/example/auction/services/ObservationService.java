package com.example.auction.services;

import com.example.auction.model.dao.AuctionEntity;
import com.example.auction.model.dao.ObservationOfAuctionEntity;
import com.example.auction.model.dao.UserEntity;
import com.example.auction.model.dto.GetObservationOfAuctionResponse;
import com.example.auction.repositories.ObservationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ObservationService {

    private final ObservationRepository observationRepository;

    private List<ObservationOfAuctionEntity> getListOfObservationsByAuction(AuctionEntity auctionEntity) {
        return observationRepository.findAllByAuctionEntity(auctionEntity);
    }

    //    private List<AddObservationOfAuctionResponse> mapListOfObservationEntityToListOfAddObservationToAuctionResponse(List<ObservationOfAuctionEntity> listOfObservationOfAuctionEntity){
//        return listOfObservationOfAuctionEntity.stream().map(this::mapObservationEntityToAddObservationOfAuctionResponse).collect(Collectors.toList());
//    }
//    private AddObservationOfAuctionResponse mapObservationEntityToAddObservationOfAuctionResponse(ObservationOfAuctionEntity observationEntity){
//        ModelMapper mapper = new ModelMapper();
//        return mapper.map(observationEntity, AddObservationOfAuctionResponse.class);
//    }
//
//    public List<AddObservationOfAuctionResponse> getListOfAddObservationOfAuctionResponseFromAuctionEntityByAuction (AuctionEntity auctionEntity){
//        List<ObservationOfAuctionEntity> listOfObservationsEntity = getListOfObservationsByAuction(auctionEntity);
//        return mapListOfObservationEntityToListOfAddObservationToAuctionResponse(listOfObservationsEntity);
//    }
    public List<GetObservationOfAuctionResponse> getListOfAddObservationOfAuctionResponseFromAuctionEntityByAuction(AuctionEntity auctionEntity) {
        List<ObservationOfAuctionEntity> listOfObservationsByAuction = getListOfObservationsByAuction(auctionEntity);
        ModelMapper mapper = new ModelMapper();
        return listOfObservationsByAuction.stream().map(observation -> mapper.map(observation, GetObservationOfAuctionResponse.class)).collect(Collectors.toList());
    }

    public boolean checkIfUserObserveThatAuction(String userEmail, AuctionEntity auctionEntity) {
        List<ObservationOfAuctionEntity> listOfObservationOfActionEntity = getListOfObservationsByAuction(auctionEntity);
        return listOfObservationOfActionEntity.stream().map(ObservationOfAuctionEntity::getUserEntity).anyMatch(userEntity -> userEntity.getEmailAddress().equals(userEmail));
    }

    public boolean checkIfObservationByAuctionAndUserDoesntExist(AuctionEntity auctionEntity, UserEntity userEntity) {
        return observationRepository.findByAuctionEntityAndUserEntity(auctionEntity, userEntity).isEmpty();
    }

    public ObservationOfAuctionEntity createObservationEtity(AuctionEntity auctionEntity, UserEntity userEntity) {
        ObservationOfAuctionEntity observationOfAuctionEntity = new ObservationOfAuctionEntity();
        observationOfAuctionEntity.setAuctionEntity(auctionEntity);
        observationOfAuctionEntity.setUserEntity(userEntity);
        return observationOfAuctionEntity;

    }

    public void saveObservationEntityToDB(ObservationOfAuctionEntity observationOfAuctionEntity) {
        observationRepository.save(observationOfAuctionEntity);
    }
}
