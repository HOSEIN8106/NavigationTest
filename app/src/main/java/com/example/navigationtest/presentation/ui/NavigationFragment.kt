package com.example.navigationtest.presentation.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.navigationtest.R
import com.example.navigationtest.databinding.FragmentNavigationBinding
import com.example.navigationtest.presentation.viewModel.DirectionViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.maplibre.android.camera.CameraPosition
import org.maplibre.android.camera.CameraUpdateFactory
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.MapLibreMap
import org.maplibre.android.maps.Style
import org.maplibre.android.style.layers.LineLayer
import org.maplibre.android.style.layers.PropertyFactory.*
import org.maplibre.android.style.sources.GeoJsonSource
import org.maplibre.geojson.LineString
import org.maplibre.geojson.Point

@AndroidEntryPoint
class NavigationFragment : Fragment() {

    private lateinit var binding: FragmentNavigationBinding
    private lateinit var mapLibreMap: MapLibreMap
    private val viewModel: DirectionViewModel by viewModels()
    private var isMapReady = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_navigation, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mapView.onCreate(savedInstanceState)

        binding.mapView.getMapAsync { map ->
            mapLibreMap = map

            val maptilerStyle = "https://api.maptiler.com/maps/streets/style.json?key=QT1Rx8lHwnsuhaR9TZqF"

            mapLibreMap.setStyle(Style.Builder().fromUri(maptilerStyle)) { style ->
                isMapReady = true

                // موقعیت اولیه دوربین (تهران)
                mapLibreMap.cameraPosition = CameraPosition.Builder()
                    .target(LatLng(35.6892, 51.3890))
                    .zoom(12.0)
                    .build()

                // افزودن سورس GeoJSON برای مسیر
                if (style.getSource("route-source") == null) {
                    style.addSource(GeoJsonSource("route-source"))
                }

                // افزودن لایه خط برای مسیر
                if (style.getLayer("route-layer") == null) {
                    style.addLayer(
                        LineLayer("route-layer", "route-source").withProperties(
                            lineColor(Color.BLUE),
                            lineWidth(4f),
                            lineJoin("round")
                        )
                    )
                }

                observeRoute()
            }
        }

        binding.btnGetRoute.setOnClickListener {
            val origin = binding.etOrigin.text.toString()
            val destination = binding.etDestination.text.toString()
            viewModel.fetchRoute(origin, destination)
        }
    }

    private fun observeRoute() {
        viewModel.routePoints.observe(viewLifecycleOwner) { points ->
            if (points.isNotEmpty() && isMapReady) {
                val lineString = LineString.fromLngLats(
                    points.map { Point.fromLngLat(it.longitude(), it.latitude()) }
                )

                mapLibreMap.getStyle()?.getSourceAs<GeoJsonSource>("route-source")?.setGeoJson(lineString)

                // حرکت دوربین به نقطه‌ی شروع مسیر
                val start = points.first()
                moveCameraTo(mapLibreMap, start.latitude(), start.longitude())
            }
        }
    }

    private fun moveCameraTo(map: MapLibreMap, latitude: Double, longitude: Double, zoomLevel: Double = 14.0) {
        val cameraPosition = CameraPosition.Builder()
            .target(LatLng(latitude, longitude))
            .zoom(zoomLevel)
            .build()

        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    // Lifecycle
    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.mapView.onDestroy()
    }
}
