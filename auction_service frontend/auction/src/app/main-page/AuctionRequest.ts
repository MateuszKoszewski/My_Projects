export class AuctionRequest {
  private searchingTag: string;
  private county: string;
  private city: string;
  private category: string;

  constructor(searchingTag: string, county: string, city: string, category: string) {
    this.searchingTag = searchingTag;
    this.county = county;
    this.city = city;
    this.category = category;
  }
}