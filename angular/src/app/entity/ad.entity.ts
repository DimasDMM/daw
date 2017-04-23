import {User} from "./user.entity";

export class Ad {
  public id:number;
  public author:User;
  public title:string;
  public url:string;
  public weight:number;

  public limDateStart:Date;
  public limDateEnd:Date;
  public limViews:number;
  public limClicks:number;
  public clicks:number;
  public views:number;

  public dateInsert:Date;
}
