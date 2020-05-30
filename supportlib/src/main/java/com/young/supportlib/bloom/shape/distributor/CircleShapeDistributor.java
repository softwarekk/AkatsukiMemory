package com.young.supportlib.bloom.shape.distributor;


import com.young.supportlib.bloom.particle.BloomParticle;
import com.young.supportlib.bloom.shape.ParticleCircleShape;
import com.young.supportlib.bloom.shape.ParticleShape;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description The circle shape distributor for all particles.
 */
public class CircleShapeDistributor extends ParticleShapeDistributor {
    @Override
    public ParticleShape getShape(BloomParticle particle) {
        return new ParticleCircleShape(particle.getInitialX(), particle.getInitialY(), particle.getRadius());
    }
}
