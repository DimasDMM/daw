import {User} from "./user.entity";

export class Comment {
  public id:number;
  public author:User;
  public comment:string;
  public dateInsert:Date;
}
