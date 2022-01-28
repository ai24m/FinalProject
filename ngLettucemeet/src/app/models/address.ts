export class Address {
  id: number;
  street1: string;
  street2: string | undefined;
  city: string;
  state: string;
  zip: string;
  constructor(
    id: number = 0,
    street1: string = '',
    street2?: string,
    city: string = '',
    state: string = '',
    zip: string = ''
  ) {
    this.id = id;
    this.street1 = street1;
    this.street2 = street2;
    this.state = state;
    this.city = city;
    this.zip = zip;
  }
}
