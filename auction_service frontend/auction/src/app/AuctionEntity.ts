import { AddressEntity } from "./AddressEntity";
import { UserEntity } from "./UserEntity";


export class AuctionEntity {

  private title: String;
  private category: String;
  private description: String;
  private minPrice: number;
  private buyNowPrice: number;
  private address: AddressEntity;
  private promoted: boolean;
  private images: [];
  private user: UserEntity


}