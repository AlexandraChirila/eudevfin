/*******************************************************************************
 * Copyright (c) 2014 Development Gateway.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 *******************************************************************************/
/**
 *
 */
package org.devgateway.eudevfin.ui.common.components;

import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;
import org.devgateway.eudevfin.common.service.PagingHelper;
import org.devgateway.eudevfin.common.service.PagingItem;
import org.devgateway.eudevfin.ui.common.components.util.ListGeneratorInterface;
import org.devgateway.eudevfin.ui.common.components.util.PageableComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mihai
 * @author Alex
 */
public abstract class TableListPanel<T> extends Panel implements PageableComponent {

    private static final long serialVersionUID = -779595395189185422L;

    public static final int PAGE_SIZE = 10;
    public static final int FIRST_PAGE = 1;

    protected ListView<T> itemsListView;

    protected List<T> items = null;
    protected List<PagingItem> pagingItems = null;
    protected ListGeneratorInterface<T> listGenerator = null;


    protected abstract void populateTable();

    protected void populateHeader() {
        ;
    }

    public TableListPanel(String id, ListGeneratorInterface<T> listGenerator) {
        super(id);
        this.setOutputMarkupId(true);

        this.listGenerator = listGenerator;

        items = new ArrayList<T>();
        pagingItems = new ArrayList<PagingItem>();

        populate(FIRST_PAGE);
    }


    public void populate(int pageNumber) {
        this.generateListOfItems(pageNumber);
        this.populateHeader();
        this.populateTable();
        this.populatePaging();

    }

    @Override
    protected void onBeforeRender() {
        super.onBeforeRender();

    }

    private void populatePaging() {
        PagingPanel pagingPanel = new PagingPanel("paging-panel", new ListModel<>(this.pagingItems));
        this.add(pagingPanel);
    }

    public void reloadTable() {
        int currentPage = FIRST_PAGE;
        if (pagingItems != null && pagingItems.size() > 0) {
            PagingItem pi = pagingItems.get(0);
            currentPage = pi.getCurrentPageNo();
        }

        generateListOfItems(currentPage);
    }

    public void generateListOfItems(int pageNumber) {
        this.items.clear();
        this.pagingItems.clear();

        PagingHelper<T> results = this.listGenerator.getResultsList(pageNumber, PAGE_SIZE);
        if (results != null && results.getTotalNumOfEntities() > 0) {
            this.items.addAll(results.getEntities());
            this.pagingItems.addAll(results.createPagingItems());
        }

    }

    /* (non-Javadoc)
     * @see org.devgateway.eudevfin.dim.desktop.components.Pageable#createLink(T)
     */
    @Override
    public AjaxLink<PagingItem> createLink(final PagingItem modelObj) {
        return new PagingAjaxLink("page-number-link", Model.of(modelObj), this, pagingItems);
    }

}
