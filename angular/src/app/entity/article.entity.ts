import {User} from "./user.entity";

export class Article {
  public id:number;
  public title:string;
  public author:User;
  public comments:Comment[];
  public nComments:number;
  public dateInsert:Date;
}
