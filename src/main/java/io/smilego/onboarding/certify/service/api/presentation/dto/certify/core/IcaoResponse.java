package io.smilego.onboarding.certify.service.api.presentation.dto.certify.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.NonNull;

import java.io.Serializable;

public class IcaoResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @NonNull
    private Icao icao;

    public Icao getIcao() {
        return icao;
    }

    public void setIcao(Icao icao) {
        this.icao = icao;
    }

    public static class Icao implements Serializable {

        private static final long serialVersionUID = 1L;

        @NonNull
        private Integer faceImageConformed;

        @NonNull
        @JsonProperty("isAllConformed")
        private boolean allConformed;

        @NonNull
        @JsonProperty("requirements")
        private Requirement requirement;

        public Integer getFaceImageConformed() {
            return faceImageConformed;
        }

        public void setFaceImageConformed(Integer faceImageConformed) {
            this.faceImageConformed = faceImageConformed;
        }

        public boolean isAllConformed() {
            return allConformed;
        }

        public void setAllConformed(boolean allConformed) {
            this.allConformed = allConformed;
        }

        public Requirement getRequirement() {
            return requirement;
        }

        public void setRequirement(Requirement requirement) {
            this.requirement = requirement;
        }

        public static class Requirement implements Serializable {

            private static final long serialVersionUID = 1L;

            @NonNull
            private Blurred blurred;
            @NonNull
            private LookingAway lookingAway;
            @NonNull
            private InkMarked inkMarked;
            @NonNull
            private UnnaturalSkin unnaturalSkin;
            @NonNull
            private TooDarkLight tooDarkLight;
            @NonNull
            private WashedOut washedOut;
            @NonNull
            private Pixelation pixelation;
            @NonNull
            private HairAcrossEyes hairAcrossEyes;
            @NonNull
            private EyesClosed eyesClosed;
            @NonNull
            private VariedBackground variedBackground;
            @NonNull
            @JsonProperty("roll_Pitch_Yaw")
            private RollPitchYaw rollPitchYaw;
            @NonNull
            private FlashReflectionOnSkin flashReflectionOnSkin;
            @NonNull
            private RedEyes redEyes;
            @NonNull
            private ShadowsBehindHead shadowsBehindHead;
            @NonNull
            private ShadowsAcrossFace shadowsAcrossFace;
            @NonNull
            private DarkTintedLenses darkTintedLenses;
            @NonNull
            private FlashReflectionOnLenses flashReflectionOnLenses;
            @NonNull
            private FramesTooHeavy framesTooHeavy;
            @NonNull
            private FramesCoveringEyes framesCoveringEyes;
            @NonNull
            @JsonProperty("hat_Cap")
            private HatCap hatCap;
            @NonNull
            private VeilOverFace veilOverFace;
            @NonNull
            private MouthOpen mouthOpen;
            @NonNull
            private OtherFaces otherFaces;

            public Blurred getBlurred() {
                return blurred;
            }

            public void setBlurred(Blurred blurred) {
                this.blurred = blurred;
            }

            public LookingAway getLookingAway() {
                return lookingAway;
            }

            public void setLookingAway(LookingAway lookingAway) {
                this.lookingAway = lookingAway;
            }

            public InkMarked getInkMarked() {
                return inkMarked;
            }

            public void setInkMarked(InkMarked inkMarked) {
                this.inkMarked = inkMarked;
            }

            public UnnaturalSkin getUnnaturalSkin() {
                return unnaturalSkin;
            }

            public void setUnnaturalSkin(UnnaturalSkin unnaturalSkin) {
                this.unnaturalSkin = unnaturalSkin;
            }

            public TooDarkLight getTooDarkLight() {
                return tooDarkLight;
            }

            public void setTooDarkLight(TooDarkLight tooDarkLight) {
                this.tooDarkLight = tooDarkLight;
            }

            public WashedOut getWashedOut() {
                return washedOut;
            }

            public void setWashedOut(WashedOut washedOut) {
                this.washedOut = washedOut;
            }

            public Pixelation getPixelation() {
                return pixelation;
            }

            public void setPixelation(Pixelation pixelation) {
                this.pixelation = pixelation;
            }

            public HairAcrossEyes getHairAcrossEyes() {
                return hairAcrossEyes;
            }

            public void setHairAcrossEyes(HairAcrossEyes hairAcrossEyes) {
                this.hairAcrossEyes = hairAcrossEyes;
            }

            public EyesClosed getEyesClosed() {
                return eyesClosed;
            }

            public void setEyesClosed(EyesClosed eyesClosed) {
                this.eyesClosed = eyesClosed;
            }

            public VariedBackground getVariedBackground() {
                return variedBackground;
            }

            public void setVariedBackground(VariedBackground variedBackground) {
                this.variedBackground = variedBackground;
            }

            public RollPitchYaw getRollPitchYaw() {
                return rollPitchYaw;
            }

            public void setRollPitchYaw(RollPitchYaw rollPitchYaw) {
                this.rollPitchYaw = rollPitchYaw;
            }

            public FlashReflectionOnSkin getFlashReflectionOnSkin() {
                return flashReflectionOnSkin;
            }

            public void setFlashReflectionOnSkin(FlashReflectionOnSkin flashReflectionOnSkin) {
                this.flashReflectionOnSkin = flashReflectionOnSkin;
            }

            public RedEyes getRedEyes() {
                return redEyes;
            }

            public void setRedEyes(RedEyes redEyes) {
                this.redEyes = redEyes;
            }

            public ShadowsBehindHead getShadowsBehindHead() {
                return shadowsBehindHead;
            }

            public void setShadowsBehindHead(ShadowsBehindHead shadowsBehindHead) {
                this.shadowsBehindHead = shadowsBehindHead;
            }

            public ShadowsAcrossFace getShadowsAcrossFace() {
                return shadowsAcrossFace;
            }

            public void setShadowsAcrossFace(ShadowsAcrossFace shadowsAcrossFace) {
                this.shadowsAcrossFace = shadowsAcrossFace;
            }

            public DarkTintedLenses getDarkTintedLenses() {
                return darkTintedLenses;
            }

            public void setDarkTintedLenses(DarkTintedLenses darkTintedLenses) {
                this.darkTintedLenses = darkTintedLenses;
            }

            public FlashReflectionOnLenses getFlashReflectionOnLenses() {
                return flashReflectionOnLenses;
            }

            public void setFlashReflectionOnLenses(FlashReflectionOnLenses flashReflectionOnLenses) {
                this.flashReflectionOnLenses = flashReflectionOnLenses;
            }

            public FramesTooHeavy getFramesTooHeavy() {
                return framesTooHeavy;
            }

            public void setFramesTooHeavy(FramesTooHeavy framesTooHeavy) {
                this.framesTooHeavy = framesTooHeavy;
            }

            public FramesCoveringEyes getFramesCoveringEyes() {
                return framesCoveringEyes;
            }

            public void setFramesCoveringEyes(FramesCoveringEyes framesCoveringEyes) {
                this.framesCoveringEyes = framesCoveringEyes;
            }

            public HatCap getHatCap() {
                return hatCap;
            }

            public void setHatCap(HatCap hatCap) {
                this.hatCap = hatCap;
            }

            public VeilOverFace getVeilOverFace() {
                return veilOverFace;
            }

            public void setVeilOverFace(VeilOverFace veilOverFace) {
                this.veilOverFace = veilOverFace;
            }

            public MouthOpen getMouthOpen() {
                return mouthOpen;
            }

            public void setMouthOpen(MouthOpen mouthOpen) {
                this.mouthOpen = mouthOpen;
            }

            public OtherFaces getOtherFaces() {
                return otherFaces;
            }

            public void setOtherFaces(OtherFaces otherFaces) {
                this.otherFaces = otherFaces;
            }

            public static class Blurred implements Serializable {

                private static final long serialVersionUID = 1L;

                @JsonProperty("isConformed")
                private boolean conformed;

                public boolean isConformed() {
                    return conformed;
                }

                public void setConformed(boolean conformed) {
                    this.conformed = conformed;
                }

            }

            public static class LookingAway implements Serializable {

                private static final long serialVersionUID = 1L;

                @JsonProperty("isConformed")
                private boolean conformed;

                public boolean isConformed() {
                    return conformed;
                }

                public void setConformed(boolean conformed) {
                    this.conformed = conformed;
                }

            }

            public static class InkMarked implements Serializable {

                private static final long serialVersionUID = 1L;

                @JsonProperty("isConformed")
                private boolean conformed;

                public boolean isConformed() {
                    return conformed;
                }

                public void setConformed(boolean conformed) {
                    this.conformed = conformed;
                }

            }

            public static class UnnaturalSkin implements Serializable {

                private static final long serialVersionUID = 1L;

                @JsonProperty("isConformed")
                private boolean conformed;

                public boolean isConformed() {
                    return conformed;
                }

                public void setConformed(boolean conformed) {
                    this.conformed = conformed;
                }

            }

            public static class TooDarkLight implements Serializable {

                private static final long serialVersionUID = 1L;

                @JsonProperty("isConformed")
                private boolean conformed;

                public boolean isConformed() {
                    return conformed;
                }

                public void setConformed(boolean conformed) {
                    this.conformed = conformed;
                }

            }

            public static class WashedOut implements Serializable {

                private static final long serialVersionUID = 1L;

                @JsonProperty("isConformed")
                private boolean conformed;

                public boolean isConformed() {
                    return conformed;
                }

                public void setConformed(boolean conformed) {
                    this.conformed = conformed;
                }

            }

            public static class Pixelation implements Serializable {

                private static final long serialVersionUID = 1L;

                @JsonProperty("isConformed")
                private boolean conformed;

                public boolean isConformed() {
                    return conformed;
                }

                public void setConformed(boolean conformed) {
                    this.conformed = conformed;
                }

            }

            public static class HairAcrossEyes implements Serializable {

                private static final long serialVersionUID = 1L;

                @JsonProperty("isConformed")
                private boolean conformed;

                public boolean isConformed() {
                    return conformed;
                }

                public void setConformed(boolean conformed) {
                    this.conformed = conformed;
                }

            }

            public static class EyesClosed implements Serializable {

                private static final long serialVersionUID = 1L;

                @JsonProperty("isConformed")
                private boolean conformed;

                public boolean isConformed() {
                    return conformed;
                }

                public void setConformed(boolean conformed) {
                    this.conformed = conformed;
                }

            }

            public static class VariedBackground implements Serializable {

                private static final long serialVersionUID = 1L;

                @JsonProperty("isConformed")
                private boolean conformed;

                public boolean isConformed() {
                    return conformed;
                }

                public void setConformed(boolean conformed) {
                    this.conformed = conformed;
                }

            }

            public static class RollPitchYaw implements Serializable {

                private static final long serialVersionUID = 1L;

                @JsonProperty("isConformed")
                private boolean conformed;

                public boolean isConformed() {
                    return conformed;
                }

                public void setConformed(boolean conformed) {
                    this.conformed = conformed;
                }

            }

            public static class FlashReflectionOnSkin implements Serializable {

                private static final long serialVersionUID = 1L;

                @JsonProperty("isConformed")
                private boolean conformed;

                public boolean isConformed() {
                    return conformed;
                }

                public void setConformed(boolean conformed) {
                    this.conformed = conformed;
                }

            }

            public static class RedEyes implements Serializable {

                private static final long serialVersionUID = 1L;

                @JsonProperty("isConformed")
                private boolean conformed;

                public boolean isConformed() {
                    return conformed;
                }

                public void setConformed(boolean conformed) {
                    this.conformed = conformed;
                }

            }

            public static class ShadowsBehindHead implements Serializable {

                private static final long serialVersionUID = 1L;

                @JsonProperty("isConformed")
                private boolean conformed;

                public boolean isConformed() {
                    return conformed;
                }

                public void setConformed(boolean conformed) {
                    this.conformed = conformed;
                }

            }

            public static class ShadowsAcrossFace implements Serializable {

                private static final long serialVersionUID = 1L;

                @JsonProperty("isConformed")
                private boolean conformed;

                public boolean isConformed() {
                    return conformed;
                }

                public void setConformed(boolean conformed) {
                    this.conformed = conformed;
                }

            }

            public static class DarkTintedLenses implements Serializable {

                private static final long serialVersionUID = 1L;

                @JsonProperty("isConformed")
                private boolean conformed;

                public boolean isConformed() {
                    return conformed;
                }

                public void setConformed(boolean conformed) {
                    this.conformed = conformed;
                }

            }

            public static class FlashReflectionOnLenses implements Serializable {

                private static final long serialVersionUID = 1L;

                @JsonProperty("isConformed")
                private boolean conformed;

                public boolean isConformed() {
                    return conformed;
                }

                public void setConformed(boolean conformed) {
                    this.conformed = conformed;
                }

            }

            public static class FramesTooHeavy implements Serializable {

                private static final long serialVersionUID = 1L;

                @JsonProperty("isConformed")
                private boolean conformed;

                public boolean isConformed() {
                    return conformed;
                }

                public void setConformed(boolean conformed) {
                    this.conformed = conformed;
                }

            }

            public static class FramesCoveringEyes implements Serializable {

                private static final long serialVersionUID = 1L;

                @JsonProperty("isConformed")
                private boolean conformed;

                public boolean isConformed() {
                    return conformed;
                }

                public void setConformed(boolean conformed) {
                    this.conformed = conformed;
                }

            }

            public static class HatCap implements Serializable {

                private static final long serialVersionUID = 1L;

                @JsonProperty("isConformed")
                private boolean conformed;

                public boolean isConformed() {
                    return conformed;
                }

                public void setConformed(boolean conformed) {
                    this.conformed = conformed;
                }

            }

            public static class VeilOverFace implements Serializable {

                private static final long serialVersionUID = 1L;

                @JsonProperty("isConformed")
                private boolean conformed;

                public boolean isConformed() {
                    return conformed;
                }

                public void setConformed(boolean conformed) {
                    this.conformed = conformed;
                }

            }

            public static class MouthOpen implements Serializable {

                private static final long serialVersionUID = 1L;

                @JsonProperty("isConformed")
                private boolean conformed;

                public boolean isConformed() {
                    return conformed;
                }

                public void setConformed(boolean conformed) {
                    this.conformed = conformed;
                }

            }

            public static class OtherFaces implements Serializable {

                private static final long serialVersionUID = 1L;

                @JsonProperty("isConformed")
                private boolean conformed;

                public boolean isConformed() {
                    return conformed;
                }

                public void setConformed(boolean conformed) {
                    this.conformed = conformed;
                }

            }

        }

    }

}
