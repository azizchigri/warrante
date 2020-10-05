package aziz.chigri.warrantev2.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TagItem implements Parcelable {

    private String id;
    private String name;
    private int color;

    public TagItem(String id, String name, int color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    protected TagItem(Parcel in) {
        id = in.readString();
        name = in.readString();
        color = in.readInt();
    }

    public static final Creator<TagItem> CREATOR = new Creator<TagItem>() {
        @Override
        public TagItem createFromParcel(Parcel in) {
            return new TagItem(in);
        }

        @Override
        public TagItem[] newArray(int size) {
            return new TagItem[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
    }
}
