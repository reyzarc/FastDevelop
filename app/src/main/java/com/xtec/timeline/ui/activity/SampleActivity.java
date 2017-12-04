/*
 * Copyright (C) 2015 Trevor Siemens
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xtec.timeline.ui.activity;

import android.media.audiofx.LoudnessEnhancer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.xtec.timeline.R;
import com.xtec.timeline.widget.dragListView.DynamicArrayAdapter;
import com.xtec.timeline.widget.dragListView.DynamicListView;

import java.util.Arrays;
import java.util.List;

public class SampleActivity extends AppCompatActivity {

    private List<String> rowData = Arrays.asList("1", "2", "3", "4", "5");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        DynamicListView listView = (DynamicListView) findViewById(R.id.dynamic_list_view);
        final MyListAdapter adapter = new MyListAdapter(this, rowData);

        // Colors transparent portions of the row while being dragged over other views.
        adapter.fillTranslarentMobileRowBackground(getResources().getColor(android.R.color.holo_orange_dark));
        adapter.bindToListView(listView);

        adapter.setOnItemsSwappedListener(new DynamicArrayAdapter.ItemSwapListener() {
            @Override
            public void onItemsSwapped(int pos1, int pos2) {
                String old = adapter.objects.get(pos1);
                String newstr = adapter.objects.get(pos2);
                Log.e("reyzarc","the old string is==>"+old+"====>and the new is==>"+newstr);
                for (int i = 0; i < adapter.objects.size(); i++) {
                    Log.e("reyzarc","==>"+adapter.objects.get(i)+"<==");
                }
            }
        });
    }
}
