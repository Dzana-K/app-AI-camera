package org.pytorch.demo.objectdetection;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

public class DataActivity extends AppCompatActivity implements View.OnClickListener {

    private LineChartView chart;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(this) ;

        Intent intent = getIntent();
        ArrayList<String> timeList = intent.getStringArrayListExtra("timeList");
        ArrayList<Integer> detectionList = intent.getIntegerArrayListExtra("detectionList");


        Log.d("Data-time-0", timeList.get(0));
        Log.d("Data-time-1", timeList.get(1));
        Log.d("Data-time-2", timeList.get(2));
        Log.d("Data-time-3", timeList.get(3));

        Log.d("Detection-0", String.valueOf(detectionList.get(0)));
        Log.d("Detection-1", String.valueOf(detectionList.get(1)));
        Log.d("Detection-2", String.valueOf(detectionList.get(2)));
        Log.d("Detection-3", String.valueOf(detectionList.get(3)));


        chart = findViewById(R.id.chart);



        List<PointValue> values = new ArrayList<>();
        for (int i = 0; i < detectionList.size(); i++) {
            values.add(new PointValue(i, detectionList.get(i)));
        }

        Line line = new Line(values).setColor(ChartUtils.COLOR_BLUE);
        List<Line> lines = new ArrayList<>();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        Axis axisX = new Axis();
        Axis axisY = new Axis().setHasLines(true);
        axisX.setAutoGenerated(false);
        axisX.setValues(generateAxisValues(timeList));
        axisY.setName("Detections");
        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);

        chart.setLineChartData(data);

        Viewport v = new Viewport(chart.getMaximumViewport());
        v.bottom = 0;
        v.top = 12;
        chart.setMaximumViewport(v);
        chart.setCurrentViewport(v);
    }

    private List<AxisValue> generateAxisValues(List<String> dates) {
        List<AxisValue> axisValues = new ArrayList<>();
        for (int i = 0; i < dates.size(); i++) {
            axisValues.add(new AxisValue(i).setLabel(dates.get(i)));
        }
        return axisValues;
    }
    @Override
    public void onClick(View v) {
        onBackPressed();
    }
}


