package aziz.chigri.warrantev2.view;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Objects;

import aziz.chigri.warrantev2.R;
import aziz.chigri.warrantev2.model.TagItem;

public class TagPageLayout extends Fragment {


    TagItem first;
    TagItem second;


    // 1 - Create keys for our Bundle
    private static final String KEY_POSITION="position";
    private static final String NEXT_TAG ="NEXT_TAG";


    public TagPageLayout() { }


    // 2 - Method that will create a new instance of PageFragment, and add data to its bundle.
    public static TagPageLayout newInstance(int position, ArrayList<TagItem> tag) {

        // 2.1 Create new fragment
        TagPageLayout frag = new TagPageLayout();

        // 2.2 Create bundle and add it some data
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        args.putParcelableArrayList(NEXT_TAG, tag);
        frag.setArguments(args);

        return(frag);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // 3 - Get layout of PageFragment
        View result = inflater.inflate(R.layout.tag_page, container, false);

        // 4 - Get widgets from layout and serialise it
        LinearLayout firstColumn= (LinearLayout) result.findViewById(R.id.first_column);
        LinearLayout secondColumn= (LinearLayout) result.findViewById(R.id.second_column);

        // 5 - Get data from Bundle (created in method newInstance)
        int position = getArguments().getInt(KEY_POSITION, -1);
        ArrayList<TagItem> tags = getArguments().getParcelableArrayList(NEXT_TAG);

        for (int i = 0; i < Objects.requireNonNull(tags).size(); i++) {
            final TagItem tag = tags.get(i);
            Button button = new Button(getContext());
            button.setText(tag.getName());
            button.setTextColor(Color.WHITE);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(450, 140);
            params.setMargins(10,10,10,10);
            button.setLayoutParams(params);
            button.setBackgroundResource(R.drawable.tag_button_border);
            GradientDrawable backgroundShape = (GradientDrawable)button.getBackground();
            int myColor = ContextCompat.getColor(Objects.requireNonNull(getContext()), tag.getColor());
            backgroundShape.setColor(myColor);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "text is " + tag.getName(), Toast.LENGTH_SHORT).show();
                }
            });
            if (i % 2 == 0) {
                firstColumn.addView(button);
            } else {
                secondColumn.addView(button);
            }
        }


        Log.e(getClass().getSimpleName(), "onCreateView called for fragment number "+position);

        return result;
    }
}
