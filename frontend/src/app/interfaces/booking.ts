export interface Booking {
    carNumber: string;
    phoneNumber: string;
    slotId?: number;
    rangeFrom?: number;
    rangeTo?: number;
    tariff?: number;
}