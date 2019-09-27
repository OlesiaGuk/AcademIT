package guk.model;

public class ConversionModel {
    private double temperature;

    public ConversionModel() {
    }

    public ConversionModel(double temperature) {
        this.temperature = temperature;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public enum ScalesEnum {
        CELSIUS("Градусы Цельсия") {
            @Override
            Scale createNewScaleObject() {
                return new CelsiusScale();
            }
        },

        FAHRENHEIT("Градусы Фаренгейта") {
            @Override
            Scale createNewScaleObject() {
                return new FahrenheitScale();
            }
        },

        KELVIN("Кельвины") {
            @Override
            Scale createNewScaleObject() {
                return new KelvinScale();
            }
        };

        String scaleDegreesName;

        ScalesEnum(String scaleDegreesName) {
            this.scaleDegreesName = scaleDegreesName;
        }

        public String getScaleDegreesName() {
            return scaleDegreesName;
        }

        abstract Scale createNewScaleObject();

        @Override //чтобы в комбобоксах выводилось название шкал на русском
        public String toString() {
            return scaleDegreesName;
        }
    }

    private String findEnumName(String scaleDegreesName) {
        for (ScalesEnum e : ScalesEnum.values()) {
            if (e.getScaleDegreesName().equals(scaleDegreesName)) {
                return e.name();
            }
        }
        return null;
    }

    public double convert(String inputScale, String outputScale) {
        double interimTemp = ScalesEnum.valueOf(findEnumName(inputScale)).createNewScaleObject().convertToCelsius(temperature);

        return ScalesEnum.valueOf(findEnumName(outputScale)).createNewScaleObject().convertFromCelsius(interimTemp);
    }
}