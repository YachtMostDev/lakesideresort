package nl.yacht.lakesideresort.domain;

public class LakeTrip extends Trip{
    public String type = "M";
    public LakeTrip(int tripNumber) {
        super(tripNumber);
    }

    @Override
    public String getTripType() {
       return this.type;
    }

}
