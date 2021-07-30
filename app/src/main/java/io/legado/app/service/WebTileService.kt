package io.legado.app.service

import android.content.Intent
import android.os.Build
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import androidx.annotation.RequiresApi
import io.legado.app.constant.IntentAction

@RequiresApi(Build.VERSION_CODES.N)
class WebTileService : TileService() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            IntentAction.start -> {
                qsTile.state = Tile.STATE_ACTIVE
                qsTile.updateTile()
            }
            IntentAction.stop -> {
                qsTile.state = Tile.STATE_INACTIVE
                qsTile.updateTile()
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onStartListening() {
        super.onStartListening()
        if (WebService.isRun) {
            qsTile.state = Tile.STATE_ACTIVE
            qsTile.updateTile()
        } else {
            qsTile.state = Tile.STATE_INACTIVE
            qsTile.updateTile()
        }
    }

    override fun onClick() {
        super.onClick()
        if (WebService.isRun) {
            WebService.stop(this)
        } else {
            WebService.start(this)
        }
    }

}