// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package git4idea.merge.dialog

import com.intellij.ide.ui.laf.darcula.DarculaUIUtil
import com.intellij.util.ui.JBDimension
import com.intellij.util.ui.JBUI
import net.miginfocom.layout.CC
import net.miginfocom.layout.LC
import net.miginfocom.swing.MigLayout
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Insets
import java.awt.geom.Path2D
import java.awt.geom.Rectangle2D
import javax.swing.JLabel
import javax.swing.JPanel

class CmdLabel(cmd: String,
               private val border: Insets = Insets(1, 1, 1, 1),
               private val componentSize: Dimension = JBDimension(100, 28)) : JPanel() {

  init {
    layout = MigLayout(LC().insets("0"))

    val label = JLabel(cmd)
    val gapY = (componentSize.height - label.preferredSize.height) / 2
    add(label,
        CC().gapY("${gapY}px", "0").gapBefore("${JBUI.scale(6)}px"))
  }

  override fun getPreferredSize() = componentSize

  override fun paintBorder(g: Graphics) {
    val lw = DarculaUIUtil.LW.float
    val bw = DarculaUIUtil.BW.get()

    val borderShape: Path2D = Path2D.Float(Path2D.WIND_EVEN_ODD)

    borderShape.append(Rectangle2D.Float(0f, bw.toFloat() + 1, width.toFloat(), height - (bw.toFloat() + 1) * 2), false)
    borderShape.append(Rectangle2D.Float(lw * border.left, bw + lw + 1,
                                         width.toFloat() - lw * border.right, height - (bw + lw + 1) * 2), false)

    val g2 = g as Graphics2D

    g2.color = DarculaUIUtil.getOutlineColor(true, false)
    g2.fill(borderShape)
  }
}