import { Address } from './address';
import { User } from './user';

export class Market {
  id: number;
  name: string | undefined;
  description: string | undefined;
  imageUrl: string | undefined;
  marketDate: string | undefined;
  createTime: string | undefined;
  updateTime: string | undefined;
  user: User | undefined;
  address: Address;
  constructor(
    id: number = 0,
    name?: string,
    description?: string,
    imageUrl?: string,
    marketDate?: string,
    createTime?: string,
    updateTime?: string,
    user?: User,
    address: Address = new Address()
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.imageUrl = imageUrl;
    this.marketDate = marketDate;
    this.createTime = createTime;
    this.updateTime = updateTime;
    this.user = user;
    this.address = address;
  }
}
