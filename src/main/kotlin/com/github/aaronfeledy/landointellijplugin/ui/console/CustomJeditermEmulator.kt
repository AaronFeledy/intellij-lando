package com.github.aaronfeledy.landointellijplugin.ui.console

import com.jediterm.terminal.Terminal
import com.jediterm.terminal.TerminalDataStream
import com.jediterm.terminal.emulator.JediEmulator

class CustomJeditermEmulator(dataStream: TerminalDataStream?, terminal: Terminal?) : JediEmulator(dataStream, terminal) {
    override fun processChar(ch: Char, terminal: Terminal) {
        when (ch) {
            '\r' -> {
                terminal.carriageReturn()
            }
            '\n' -> {
                terminal.newLine()
            }
            else -> super.processChar(ch, terminal)
        }
    }

    override fun unsupported(vararg sequenceChars: Char) {
        super.unsupported(*sequenceChars)
    }

}