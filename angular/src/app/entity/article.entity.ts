import {User} from "./user.entity";

export class Article {
  public id:number;
  public category:string;
  public title:string;
  public content:string;
  public author:User;
  public source:string;

  public tags:string[];
  public visible:boolean;
  public comments:Comment[];
  public nComments:number;
  public views:number;

  public dateInsert:Date;
}
