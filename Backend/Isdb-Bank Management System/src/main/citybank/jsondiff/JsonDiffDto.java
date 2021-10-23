package com.citybank.jsondiff;

import java.util.List;

public class JsonDiffDto {
    private List<PresentData> presentDataList;
    private List<PreviousData> previousDataList;

    public List<PresentData> getPresentDataList() {
        return presentDataList;
    }

    public void setPresentDataList(List<PresentData> presentDataList) {
        this.presentDataList = presentDataList;
    }

    public List<PreviousData> getPreviousDataList() {
        return previousDataList;
    }

    public void setPreviousDataList(List<PreviousData> previousDataList) {
        this.previousDataList = previousDataList;
    }
}
