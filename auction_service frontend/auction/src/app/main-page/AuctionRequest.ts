export class AuctionRequest {
  searchingTag: string;
  county: string;
  city: string;
  category: string;
  userEmail: string

  constructor(searchingTag: string, county: string, city: string, category: string, userEmail: string) {
    this.searchingTag = searchingTag;
    this.county = county;
    this.city = city;
    this.category = category;
    this.userEmail = userEmail;
  }
}