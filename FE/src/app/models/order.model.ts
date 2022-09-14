export interface Order {
  order_Id?: number;
  user_Id: number;
  productIds: Array<number>;
  quantities: Array<number>;
}