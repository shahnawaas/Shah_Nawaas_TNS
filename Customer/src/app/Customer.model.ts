export interface Customer {
    id: number | null;  // Matches backend primary key
    name: string;
    orderId: number;
    email: string;
    phone: string;
  }