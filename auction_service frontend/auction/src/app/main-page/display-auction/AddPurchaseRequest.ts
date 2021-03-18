export class AddPurchaseRequest {
  private userEmail: String;
  private auctionId: number;

  constructor(userEmail: String, auctionId: number) {
    this.userEmail = userEmail;
    this.auctionId = auctionId;
  }
}