package me.trixkz.customchatcolor.chatcolor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ColorSet<R, G, B> {

    private R red = null;
    private G green = null;
    private B blue = null;
}
