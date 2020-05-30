package com.young.supportlib.bloom.shape.distributor;


import com.young.supportlib.bloom.particle.BloomParticle;
import com.young.supportlib.bloom.shape.ParticleRectShape;
import com.young.supportlib.bloom.shape.ParticleShape;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description The rect shape distributor without rebound corners for all particles.
 */
public class RectShapeDistributor extends ParticleShapeDistributor {

    @Override
    public ParticleShape getShape(BloomParticle particle) {
        return new ParticleRectShape(particle.getInitialX(), particle.getInitialY(), particle.getRadius());
    }
}
