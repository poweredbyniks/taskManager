package org.niks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.niks.entity.Task;

import java.util.Comparator;

@AllArgsConstructor
@Getter
public enum TaskSort {

    START_DATE((o1, o2) -> o1.getStartDate().compareTo(o2.getStartDate()), "start date"),
    FINISH_DATE((o1, o2) -> o1.getFinishDate().compareTo(o2.getFinishDate()), "finish date"),
    STATUS((o1, o2) -> o1.getTaskStatus().compareTo(o2.getTaskStatus()), "status");

    public Comparator<Task> taskComparator;
    String order;

}