package com.young.supportlib.bloom.shape.distributor;


import com.young.supportlib.bloom.particle.BloomParticle;
import com.young.supportlib.bloom.shape.ParticleShape;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description The bloom shape distributor.
 */
public abstract class ParticleShapeDistributor {
    public abstract ParticleShape getShape(BloomParticle particle);
}
