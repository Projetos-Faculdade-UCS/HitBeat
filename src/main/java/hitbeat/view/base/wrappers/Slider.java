package hitbeat.view.base.wrappers;

import io.github.palexdev.materialfx.controls.MFXSlider;
import io.github.palexdev.materialfx.skins.MFXSliderSkin;
import javafx.scene.control.Skin;

public class Slider extends MFXSlider{

    public Slider() {
        super();
    }

    public Slider(double min, double max, double value) {
        super(min, max, value);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new MySkin(this);
    }

    class MySkin extends MFXSliderSkin {
        public MySkin(MFXSlider slider) {
            super(slider);
        }
        @Override
        protected void showPopup() {
        }
    }
}
