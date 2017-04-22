import {Favourite} from "./favourite.entity";

export class User {
  id:number;
  alias:string;
  name:string;
  lastname:string;
  sex:string;

  email:string;
  roles:string[];
  favourites:Favourite;

  city:string;
  country:string;
  phone:string;
  description:string;
  personalWeb:string;
}
