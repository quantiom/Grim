package ac.grim.grimac.utils.data;

import com.google.common.base.Objects;
import lombok.Getter;
import org.bukkit.util.Vector;

public class VectorData {
    public VectorType vectorType;
    public VectorData lastVector;
    public Vector vector;

    @Getter
    private boolean isKnockback, isExplosion, isTrident, isZeroPointZeroThree, isSwimHop, isFlipSneaking, isJump = false;

    // For handling replacing the type of vector it is while keeping data
    public VectorData(Vector vector, VectorData lastVector, VectorType vectorType) {
        this.vector = vector;
        this.lastVector = lastVector;
        this.vectorType = vectorType;

        isKnockback = lastVector.isKnockback;
        isExplosion = lastVector.isExplosion;
        isTrident = lastVector.isTrident;
        isZeroPointZeroThree = lastVector.isZeroPointZeroThree;
        isSwimHop = lastVector.isSwimHop;
        isFlipSneaking = lastVector.isFlipSneaking;
        isJump = lastVector.isJump;

        addVectorType(vectorType);
    }

    public VectorData(Vector vector, VectorType vectorType) {
        this.vector = vector;
        this.vectorType = vectorType;
        addVectorType(vectorType);
    }

    public VectorData returnNewModified(Vector newVec, VectorType type) {
        return new VectorData(newVec, this, type);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(vectorType, lastVector, vector);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VectorData)) return false;
        VectorData that = (VectorData) o;
        return vectorType == that.vectorType && Objects.equal(lastVector, that.lastVector) && Objects.equal(vector, that.vector);
    }

    private void addVectorType(VectorType type) {
        switch (type) {
            case Knockback:
                isKnockback = true;
                break;
            case Explosion:
                isExplosion = true;
                break;
            case Trident:
                isTrident = true;
                break;
            case ZeroPointZeroThree:
                isZeroPointZeroThree = true;
                break;
            case Swimhop:
                isSwimHop = true;
                break;
            case Flip_Sneaking:
                isFlipSneaking = true;
                break;
            case Jump:
                isJump = true;
                break;
        }
    }

    // TODO: For debugging everything should have it's own type!
    // Would make false positives really easy to fix
    // But seriously, we could trace the code to find the mistake
    public enum VectorType {
        Normal,
        Swimhop,
        Climbable,
        Knockback,
        HackyClimbable,
        Teleport,
        SkippedTicks,
        Explosion,
        InputResult,
        StuckMultiplier,
        Spectator,
        Dead,
        Jump,
        SurfaceSwimming,
        SwimmingSpace,
        BestVelPicked,
        LegacySwimming,
        Elytra,
        Firework,
        Lenience,
        TridentJump,
        Trident,
        SlimePistonBounce,
        Entity_Pushing,
        ZeroPointZeroThree,
        AttackSlow,
        Flip_Sneaking
    }

    @Override
    public String toString() {
        return "VectorData{" +
                "vectorType=" + vectorType +
                ", vector=" + vector +
                '}';
    }
}
