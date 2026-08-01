The widget receiver has been removed from AndroidManifest.xml.
If you want to delete widget-related files, remove:
- VolumeWidgetProvider.kt
- app/src/main/res/xml/volume_widget_info.xml
- app/src/main/res/layout/volume_widget_layout.xml
- widget drawable resources if not used elsewhere
