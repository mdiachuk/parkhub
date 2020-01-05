package ua.com.parkhub.mapper;

//import org.junit.jupiter.api.Test;
//import ua.com.parkhub.model.ParkingModel;
//import ua.com.parkhub.persistence.entities.Address;
//import ua.com.parkhub.persistence.entities.Parking;
//import ua.com.parkhub.persistence.entities.User;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ParkingMapperTest {
//
//    @Test
//    void fromEntityToModel() {
//        Address address = new Address();
//        address.setCity("Kyiv");
//        address.setBuilding("4");
//        address.setStreet("Korolyova");
//
//        Parking parking = new Parking();
//        parking.setParkingName("1");
//        parking.setSlotsNumber(1);
//        parking.setTariff(1);
//        parking.setActive(true);
//        parking.setOwner(new User());
//        parking.setAddress(address);
//
//        ParkingMapper parkingMapper = new ParkingMapper();
//
//        ParkingModel parkingModel = parkingMapper.fromEntityToModel(parking);
//
//        assertEquals(parking.getParkingName(), parkingMapper.fromEntityToModel(parking).getParkingName());
//        assertEquals(parking.getSlotsNumber(), parkingMapper.fromEntityToModel(parking).getSlotsNumber());
//        assertEquals(parking.getTariff(), parkingMapper.fromEntityToModel(parking).getTariff());
//        assertEquals(parking.getAddress().getBuilding(), parkingMapper.fromEntityToModel(parking).getAddressModel().getBuilding());
//        assertEquals(parking.getAddress().getCity(), "Kyiv");
//        assertEquals(parking.getAddress().getStreet(), "Korolyova");
//    }
//
////    @Test
////    void fromModelToDto() {
////
////        ParkingMapper parkingMapper = new ParkingMapper();
////
////        AddressModel address = new AddressModel();
////        address.setCity("Kyiv");
////        address.setBuilding("4");
////        address.setStreet("Korolyova");
////
////        ParkingModel parkingModel = new ParkingModel("Parking1", 3, 3, true, new User(), address);
////        ParkingDTO parkingDTO = parkingMapper.fromModelToDto(parkingModel);
////
////        assertEquals(parkingModel.getParkingName(), parkingDTO.getParkingName());
////        assertEquals(parkingModel.getAddressModel().toString(), parkingDTO.getParkingAddress());
////    }
//
//}
