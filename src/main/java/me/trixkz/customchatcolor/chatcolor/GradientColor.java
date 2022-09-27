package me.trixkz.customchatcolor.chatcolor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class GradientColor {

    private ColorSet<Integer, Integer, Integer> colorCodeOne;
    private ColorSet<Integer, Integer, Integer> colorCodeTwo;
}
