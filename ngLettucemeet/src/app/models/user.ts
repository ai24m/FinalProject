import { Address } from "./address";

export class User {
  id: number;
  username: string;
  password: string;
  email: string | undefined;
  role: string | undefined;
  firstname: string;
  lastname: string;
  businessname: string;
  imageurl: string;
  address: Address | undefined;

  constructor(id: number = 0, username: string = '', password: string = '', email?: string, role?: string,
  firstname: string = '', lastname: string = '', businessname: string = '', imageurl: string = '', address?: Address) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.email = email;
    this.role = role;
    this.firstname = firstname;
    this.lastname = lastname;
    this.businessname = businessname;
    this.imageurl = imageurl;
    this.address = address;
  }
}
