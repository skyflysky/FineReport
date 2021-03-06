package com.fr.plugin.chart.designer.style.background;

import java.util.ArrayList;
import java.util.List;

import com.fr.chart.chartattr.*;
import com.fr.design.file.HistoryTemplateListPane;
import com.fr.design.gui.frpane.AbstractAttrNoScrollPane;
import com.fr.design.mainframe.BaseJForm;
import com.fr.design.mainframe.JTemplate;
import com.fr.design.mainframe.chart.PaneTitleConstants;
import com.fr.design.mainframe.chart.gui.style.legend.AutoSelectedPane;
import com.fr.design.mainframe.chart.gui.style.ThirdTabPane;
import com.fr.general.ComparatorUtils;
import com.fr.plugin.chart.designer.style.VanChartStylePane;
import com.fr.plugin.chart.vanchart.VanChart;

/**
 * 属性表, 图表样式-背景界面.
 */
public class VanChartAreaPane extends ThirdTabPane<VanChart> implements AutoSelectedPane {
    private static final long serialVersionUID = 3961996287868450144L;

    private static final int PRE_WIDTH = 220;

    private VanChartAreaBackgroundPane areaPane;
    private VanChartAreaBackgroundPane plotPane;

    public VanChartAreaPane(Plot plot, VanChartStylePane parent) {
        super(plot, parent);
    }

    /**
     * 界面 使用标题
     * @return     标题
     */
    public String title4PopupWindow() {
        return PaneTitleConstants.CHART_STYLE_AREA_TITLE;
    }

    @Override
    protected List<NamePane> initPaneList(Plot plot, AbstractAttrNoScrollPane parent) {
        List<NamePane> paneList = new ArrayList<NamePane>();

        areaPane = new VanChartAreaBackgroundPane(false, parent);
        plotPane = new VanChartAreaBackgroundPane(true, parent);

        JTemplate jTemplate = HistoryTemplateListPane.getInstance().getCurrentEditingTemplate();
        if (jTemplate.isJWorkBook() || jTemplate.getEditingReportIndex() == BaseJForm.ELEMENTCASE_TAB) {
            //表单中的图表组件的图表区挪到控件属性表的样式中了
            paneList.add(new NamePane(areaPane.title4PopupWindow(), areaPane));
        }else if(jTemplate.isChartBook()){
            paneList.add(new NamePane(areaPane.title4PopupWindow(), areaPane));
        }

        if(plot.isSupportPlotBackground()) {
            paneList.add(new NamePane(plotPane.title4PopupWindow(), plotPane));
        }
        return paneList;
    }

    @Override
    protected int getContentPaneWidth() {
        return PRE_WIDTH;
    }

    /**
     * 更新界面
     */
    public void populateBean(VanChart chart) {
        areaPane.populateBean(chart);
        plotPane.populateBean(chart);
    }

    /**
     * 保存界面属性.
     */
    @Override
    public void updateBean(VanChart chart) {
        areaPane.updateBean(chart);
        plotPane.updateBean(chart);
    }

    /**
     * 设置选中的界面id
     */
    public void setSelectedIndex(String id) {
        for (int i = 0; i < paneList.size(); i++) {
            if (ComparatorUtils.equals(id, nameArray[i])) {
                tabPane.setSelectedIndex(i);
                break;
            }
        }
    }
}


