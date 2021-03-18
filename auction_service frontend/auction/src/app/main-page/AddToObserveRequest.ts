export class AddToObserveRequest {
  private auctionId: number;
  private userEmail: String;

  constructor(actionId: number, userEmail: String) {
    this.auctionId = actionId;
    this.userEmail = userEmail;
  }
}