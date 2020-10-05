package aziz.chigri.warrantev2.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

import aziz.chigri.warrantev2.model.TagItem;
import aziz.chigri.warrantev2.view.TagPageLayout;

public class TagViewPagerAdapter extends FragmentPagerAdapter {

    // 1 - Array of colors that will be passed to PageFragment
    private ArrayList<TagItem> tagList;

    // 2 - Default Constructor
    public TagViewPagerAdapter(FragmentManager mgr, ArrayList<TagItem> tagList) {
        super(mgr, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.tagList = tagList;
    }

    @Override
    public int getCount() {
        if (tagList.size() % 8 == 0) return tagList.size()/8;
        else return tagList.size()/8+1;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        int start = position * 8;
        int end = Math.min(position * 8 + 8, tagList.size());

        // 4 - Page to return
        return(TagPageLayout.newInstance(position, new ArrayList<>(this.tagList.subList(start, end))));
    }
}
