package ua.com.parkhub.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ua.com.parkhub.exceptions.ParkingDoesntExistException;
import ua.com.parkhub.model.AddressModel;
import ua.com.parkhub.model.ParkingInfoModel;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.persistence.impl.AddressDAO;
import ua.com.parkhub.persistence.impl.ParkingDAO;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ParkingServiceTest {

    @Mock
    private ParkingDAO parkingDAO;
    @Mock
    private AddressDAO addressDAO;


    @Mock
    private ParkingModel parkingModel;
    @Mock
    private AddressModel addressModel;
    @Mock
    private List<ParkingModel> parkingModelList;


    @InjectMocks
    private ParkingService parkingService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void positiveResultTest_findParkingById() {

        Mockito.when(parkingDAO.findElementById(1)).thenReturn(Optional.of(parkingModel));

        assertSame(parkingModel, parkingService.findParkingById(1));
    }

    @Test
    void negativeResultTest_findParkingById() {

        Mockito.when(parkingDAO.findElementById(1000)).thenReturn(Optional.empty());

        assertThrows(ParkingDoesntExistException.class, () -> parkingService.findParkingById(1000));
    }

    @Test
    void findAllParkingTest() {

        Mockito.when(parkingDAO.findAll()).thenReturn(parkingModelList);

        assertSame(parkingModelList, parkingService.findAllParking());
    }

    @Test
    void findAllParkingByOwnerIdTest() {

        Mockito.when(parkingDAO.findAllParkingByOwnerId(1L)).thenReturn(parkingModelList);

        assertSame(parkingModelList, parkingService.findAllParkingByOwnerId(1L));
    }

    @Test
    void getNotEmptyFieldNamesFromSpecificParkingTest() {

        ParkingModel parkingModelTest = new ParkingModel();
        ParkingInfoModel parkingInfoModel = new ParkingInfoModel();
        parkingInfoModel.setParkingName("Parking");
        parkingInfoModel.setSlotsNumber(100);

        assertSame("parkingName", parkingService.getNotEmptyFieldNamesFromSpecificParking(parkingInfoModel).get(0));
        assertSame("slotsNumber", parkingService.getNotEmptyFieldNamesFromSpecificParking(parkingInfoModel).get(1));
    }

    @Test
    void updateParkingTest() {

        ParkingModel parkingModelSpy = Mockito.spy(new ParkingModel());
        ParkingInfoModel parkingInfoModelSpy = Mockito.spy(new ParkingInfoModel());
        parkingInfoModelSpy.setParkingName("");
        parkingModelSpy.setInfo(parkingInfoModelSpy);

        ParkingModel parkingModelTest = new ParkingModel();
        ParkingInfoModel parkingInfoModelTest = new ParkingInfoModel();
        parkingInfoModelTest.setParkingName("Parking");
        parkingInfoModelTest.setSlotsNumber(100);
        parkingModelTest.setInfo(parkingInfoModelTest);

        Mockito.when(parkingDAO.findElementById(1)).thenReturn(Optional.of(parkingModelSpy));
        Mockito.when(addressDAO.addWithResponse(addressModel)).thenReturn(addressModel);

        parkingService.updateParking(1L, parkingModelTest);

        verify(parkingDAO, times(1)).findElementById(1L);
    }
}