import { BookingInfo } from '../interfaces/bookingInfo';

export interface Payment {
    id: number;
    price: number;
    slotNumber: string;
    booking: BookingInfo;
}
