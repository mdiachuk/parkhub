package ua.com.parkhub.model.comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.model.SlotModel;

import java.util.Comparator;

@Component
public class SlotComparator implements Comparator<SlotModel> {

    @Override
    public int compare(SlotModel one, SlotModel two) {
        return (int) (one.getId() - two.getId());
    }
}
