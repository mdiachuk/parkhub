package ua.com.parkhub;

import org.modelmapper.ModelMapper;
import ua.com.parkhub.dto.AddressDTO;
import ua.com.parkhub.model.Address;

public class Runner {
    public static void main(String[] args) {
        /*Parking parking = new Parking();
        parking.setId(1L);
        parking.setOwner(new User());
        parking.setParkingName("Park_hub");
        Address address = new Address();
        address.setStreet("Park_address");
        parking.setAddress(address);
        parking.setSlotsNumber(21);
        List<Slot> slots = new ArrayList<>();
        Slot slot1 = new Slot();
        slot1.setId(1L);
        Slot slot2 = new Slot();
        slot2.setId(2L);
        slots.add(slot1);
        slots.add(slot2);
        parking.setSlots(slots);
        parking.setTariff(221);
        parking.setActive(true);*/
        ModelMapper mapper = new ModelMapper();
        /*ParkingWithSlotsDTO parkingWithSlotsDTO = mapper.map(parking, ParkingWithSlotsDTO.class);
        System.out.println(parkingWithSlotsDTO);
        Booking booking = new Booking();
        booking.setId(1L);
        booking.setCheckIn(LocalDateTime.now());
        Slot slot = new Slot();
        slot.setSlotNumber("A1");
        booking.setSlot(slot);
        BookingDTO bookingDTO = mapper.map(booking, BookingDTO.class);
        System.out.println(bookingDTO);*/
        Address address = new Address();
        address.setCity("Kyiv");
        address.setStreet("DN");
        address.setBuilding("21");
        AddressDTO addressDTO = mapper.map(address, AddressDTO.class);
        System.out.println(addressDTO);



    }
}
