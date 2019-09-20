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
            Scale createNewScaleObject(double temp) {
                return new CelsiusScale(temp);
            }
        },

        FAHRENHEIT("Градусы Фаренгейта") {
            @Override
            Scale createNewScaleObject(double temp) {
                return new FahrenheitScale(temp);
            }
        },

        KELVIN("Кельвины") {
            @Override
            Scale createNewScaleObject(double temp) {
                return new KelvinScale(temp);
            }
        };

        String scaleDegreesName;

        ScalesEnum(String scaleDegreesName) {
            this.scaleDegreesName = scaleDegreesName;
        }

        public String getScaleDegreesName() {
            return scaleDegreesName;
        }

        abstract Scale createNewScaleObject(double temp);

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
        double interimTemp = ScalesEnum.valueOf(findEnumName(inputScale)).createNewScaleObject(temperature).convertToCelsius();

        return ScalesEnum.valueOf(findEnumName(outputScale)).createNewScaleObject(interimTemp).convertFromCelsius();
    }
}