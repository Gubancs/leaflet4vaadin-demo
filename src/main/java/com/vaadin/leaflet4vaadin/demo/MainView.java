package com.vaadin.leaflet4vaadin.demo;

import com.vaadin.addon.leaflet4vaadin.LeafletMap;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.DefaultMapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.MapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.ui.marker.Marker;
import com.vaadin.addon.leaflet4vaadin.types.LatLng;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * A sample Vaadin view class.
 */
@Route
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {

    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     * @param service The message service. Automatically injected Spring managed
     *                bean.
     */
    public MainView() {

        setSizeFull();

        MapOptions options = new DefaultMapOptions();
        options.setCenter(new LatLng(47.070121823, 19.2041015625));
        options.setZoom(7);
        LeafletMap leafletMap = new LeafletMap(options);
        leafletMap.setBaseUrl("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png");

        Marker marker = new Marker(options.getCenter());
        marker.setDraggable(true);
        marker.bindPopup("Hi, I'm a draggable marker!");
        marker.addTo(leafletMap);

        leafletMap.onClick((e) -> {
            Notification.show("New marker added to map.", 3000, Position.TOP_CENTER);
            Marker myMarker = new Marker(e.getLatLng());
            myMarker.bindPopup("My added marker!");
            myMarker.addTo(leafletMap);
        });

        add(leafletMap);
    }

}
