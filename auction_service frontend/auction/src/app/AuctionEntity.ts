
import { LocalizationEntity } from "./LocalizationEntity";



export class AuctionEntity {

  private title: String;
  private category: String;
  private description: String;
  private minPrice: number;
  private buyNowPrice: number;
  private address: LocalizationEntity;
  private promoted: boolean;
  private images: String[];
  private emailAddress: String;


  constructor(title: String, category: String, description: String, minPrice: number, buyNowPrice: number, address: LocalizationEntity, promoted: boolean, images: String[], emailAddress: String) {
    this.title = title;
    this.category = category;
    this.description = description;
    this.minPrice = minPrice;
    this.buyNowPrice = buyNowPrice;
    this.address = address;
    this.promoted = promoted;
    this.images = images;
    this.emailAddress = emailAddress

  }
}