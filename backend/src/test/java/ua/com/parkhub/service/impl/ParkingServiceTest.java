package ua.com.parkhub.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import ua.com.parkhub.exceptions.ParkingDoesntExistException;
import ua.com.parkhub.model.AddressModel;
import ua.com.parkhub.model.ParkingInfoModel;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.persistence.impl.AddressDAO;
import ua.com.parkhub.persistence.impl.ParkingDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ParkingServiceTest {

    @Mock
    AddressGeoService addressGeoService;

    @Mock
    private ParkingDAO parkingDAO;
    @Mock
    private AddressDAO addressDAO;


    @Mock
    private ParkingModel parkingModel;
    @Mock
    private List<ParkingModel> parkingModelList;

    @Captor
    private ArgumentCaptor<ParkingModel> valueCapture;


    @InjectMocks
    private ParkingService parkingService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void positiveResultTest_findParkingById() {

        Mockito.when(parkingDAO.findElementById(anyLong())).thenReturn(Optional.of(parkingModel));

        assertSame(parkingModel, parkingService.findParkingById(1L));
    }

    @Test
    void negativeResultTest_findParkingById() {

        Mockito.when(parkingDAO.findElementById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ParkingDoesntExistException.class, () -> parkingService.findParkingById(1000L));
    }

    @Test
    void findAllParkingTest() {

        Mockito.when(parkingDAO.findAll()).thenReturn(parkingModelList);

        assertSame(parkingModelList, parkingService.findAllParking());
    }

    @Test
    void findAllParkingByOwnerIdTest() {

        Mockito.when(parkingDAO.findAllParkingByOwnerId(anyLong())).thenReturn(parkingModelList);

        assertSame(parkingModelList, parkingService.findAllParkingByOwnerId(1L));
    }

    @Test
    void getNotEmptyFieldNamesFromSpecificParkingTest() {

        ParkingInfoModel parkingInfoModel = new ParkingInfoModel();
        parkingInfoModel.setParkingName("Parking");
        parkingInfoModel.setSlotsNumber(100);

        assertSame("parkingName", parkingService.getNotEmptyFieldNamesFromSpecificParking(parkingInfoModel).get(0));
        assertSame("slotsNumber", parkingService.getNotEmptyFieldNamesFromSpecificParking(parkingInfoModel).get(1));
    }

    @Test
    void updateParking_WithoutAddressTest() {

        ParkingModel parkingModel = new ParkingModel();

        ParkingInfoModel parkingInfoModel = new ParkingInfoModel();
        parkingInfoModel.setParkingName("");

        parkingModel.setInfo(parkingInfoModel);

        ParkingModel parkingModelTest = new ParkingModel();

        ParkingInfoModel parkingInfoModelTest = new ParkingInfoModel();
        parkingInfoModelTest.setParkingName("Name");
        parkingInfoModelTest.setSlotsNumber(100);

        parkingModelTest.setInfo(parkingInfoModelTest);

        Mockito.when(parkingDAO.findElementById(anyLong())).thenReturn(Optional.of(parkingModel));
        doNothing().when(parkingDAO).updateElement(any(ParkingModel.class));

        parkingService.updateParking(1L, parkingModelTest);

        InOrder inOrder = Mockito.inOrder(parkingDAO);

        inOrder.verify(parkingDAO, times(1)).findElementById(anyLong());
        inOrder.verify(parkingDAO, times(1)).updateElement(any(ParkingModel.class));
    }

    @Test
    void updateParking_WithAddressTest() {

        ParkingModel parkingModel = new ParkingModel();

        ParkingInfoModel parkingInfoModel = new ParkingInfoModel();
        parkingInfoModel.setParkingName("Name");

        parkingModel.setInfo(parkingInfoModel);

        ParkingModel parkingModelTest = new ParkingModel();

        AddressModel addressModel = new AddressModel();
        addressModel.setCity("Kyiv");
        addressModel.setStreet("Kokosova");
        addressModel.setBuilding("1");
        addressModel.setId(1L);

        ParkingInfoModel parkingInfoModelTest = new ParkingInfoModel();

        parkingInfoModelTest.setParkingName("Parking");
        parkingInfoModelTest.setSlotsNumber(100);
        parkingInfoModelTest.setAddressModel(addressModel);

        parkingModelTest.setInfo(parkingInfoModelTest);


        Mockito.when(parkingDAO.findElementById(anyLong())).thenReturn(Optional.of(parkingModel));
        Mockito.when(addressDAO.addWithResponse(any(AddressModel.class))).thenReturn(addressModel);
        doNothing().when(parkingDAO).updateElement(any(ParkingModel.class));

        Map<String, String> latLon = new HashMap<>();
        latLon.put("lat", "-500");
        latLon.put("lon", "-500");

        Mockito.when(addressGeoService.getLatLon(anyString())).thenReturn(latLon);

        parkingService.updateParking(1L, parkingModelTest);

        InOrder inOrder = Mockito.inOrder(parkingDAO);

        inOrder.verify(parkingDAO, times(1)).findElementById(anyLong());
        inOrder.verify(parkingDAO, times(1)).updateElement(any(ParkingModel.class));
    }

    @Test
    void updateParking_CheckParkingDao_UpdateInvocationParameterTest() {

        ParkingModel parkingModel = new ParkingModel();

        ParkingInfoModel parkingInfoModel = new ParkingInfoModel();
        parkingInfoModel.setParkingName("Name");

        parkingModel.setInfo(parkingInfoModel);

        ParkingModel parkingModelTest = new ParkingModel();

        ParkingInfoModel parkingInfoModelTest = new ParkingInfoModel();

        parkingInfoModelTest.setParkingName("Parking");
        parkingInfoModelTest.setSlotsNumber(100);

        parkingModelTest.setInfo(parkingInfoModelTest);


        Mockito.when(parkingDAO.findElementById(anyLong())).thenReturn(Optional.of(parkingModel));
        doNothing().when(parkingDAO).updateElement(any(ParkingModel.class));

        parkingService.updateParking(1L, parkingModelTest);

        verify(parkingDAO, times(1)).updateElement(valueCapture.capture());

        Assertions.assertSame(parkingModelTest.getInfo().getParkingName(), valueCapture.getValue().getInfo().getParkingName());
        Assertions.assertSame(parkingModelTest.getInfo().getSlotsNumber(), valueCapture.getValue().getInfo().getSlotsNumber());
    }
}