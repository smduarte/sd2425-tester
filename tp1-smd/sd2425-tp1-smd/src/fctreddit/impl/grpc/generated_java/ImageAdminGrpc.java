package fctreddit.impl.grpc.generated_java;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.62.2)",
    comments = "Source: src/fctreddit/impl/api/grpc/ImageAdmin.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ImageAdminGrpc {

  private ImageAdminGrpc() {}

  public static final java.lang.String SERVICE_NAME = "ImageAdmin";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<fctreddit.impl.grpc.generated_java.ImageAdminProtoBuf.AdminDeleteImageArgs,
      com.google.protobuf.Empty> getDeleteUserImageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "deleteUserImage",
      requestType = fctreddit.impl.grpc.generated_java.ImageAdminProtoBuf.AdminDeleteImageArgs.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fctreddit.impl.grpc.generated_java.ImageAdminProtoBuf.AdminDeleteImageArgs,
      com.google.protobuf.Empty> getDeleteUserImageMethod() {
    io.grpc.MethodDescriptor<fctreddit.impl.grpc.generated_java.ImageAdminProtoBuf.AdminDeleteImageArgs, com.google.protobuf.Empty> getDeleteUserImageMethod;
    if ((getDeleteUserImageMethod = ImageAdminGrpc.getDeleteUserImageMethod) == null) {
      synchronized (ImageAdminGrpc.class) {
        if ((getDeleteUserImageMethod = ImageAdminGrpc.getDeleteUserImageMethod) == null) {
          ImageAdminGrpc.getDeleteUserImageMethod = getDeleteUserImageMethod =
              io.grpc.MethodDescriptor.<fctreddit.impl.grpc.generated_java.ImageAdminProtoBuf.AdminDeleteImageArgs, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "deleteUserImage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fctreddit.impl.grpc.generated_java.ImageAdminProtoBuf.AdminDeleteImageArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new ImageAdminMethodDescriptorSupplier("deleteUserImage"))
              .build();
        }
      }
    }
    return getDeleteUserImageMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ImageAdminStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ImageAdminStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ImageAdminStub>() {
        @java.lang.Override
        public ImageAdminStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ImageAdminStub(channel, callOptions);
        }
      };
    return ImageAdminStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ImageAdminBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ImageAdminBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ImageAdminBlockingStub>() {
        @java.lang.Override
        public ImageAdminBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ImageAdminBlockingStub(channel, callOptions);
        }
      };
    return ImageAdminBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ImageAdminFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ImageAdminFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ImageAdminFutureStub>() {
        @java.lang.Override
        public ImageAdminFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ImageAdminFutureStub(channel, callOptions);
        }
      };
    return ImageAdminFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void deleteUserImage(fctreddit.impl.grpc.generated_java.ImageAdminProtoBuf.AdminDeleteImageArgs request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteUserImageMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ImageAdmin.
   */
  public static abstract class ImageAdminImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ImageAdminGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ImageAdmin.
   */
  public static final class ImageAdminStub
      extends io.grpc.stub.AbstractAsyncStub<ImageAdminStub> {
    private ImageAdminStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ImageAdminStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ImageAdminStub(channel, callOptions);
    }

    /**
     */
    public void deleteUserImage(fctreddit.impl.grpc.generated_java.ImageAdminProtoBuf.AdminDeleteImageArgs request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteUserImageMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ImageAdmin.
   */
  public static final class ImageAdminBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ImageAdminBlockingStub> {
    private ImageAdminBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ImageAdminBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ImageAdminBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.google.protobuf.Empty deleteUserImage(fctreddit.impl.grpc.generated_java.ImageAdminProtoBuf.AdminDeleteImageArgs request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteUserImageMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ImageAdmin.
   */
  public static final class ImageAdminFutureStub
      extends io.grpc.stub.AbstractFutureStub<ImageAdminFutureStub> {
    private ImageAdminFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ImageAdminFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ImageAdminFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> deleteUserImage(
        fctreddit.impl.grpc.generated_java.ImageAdminProtoBuf.AdminDeleteImageArgs request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteUserImageMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_DELETE_USER_IMAGE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_DELETE_USER_IMAGE:
          serviceImpl.deleteUserImage((fctreddit.impl.grpc.generated_java.ImageAdminProtoBuf.AdminDeleteImageArgs) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getDeleteUserImageMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              fctreddit.impl.grpc.generated_java.ImageAdminProtoBuf.AdminDeleteImageArgs,
              com.google.protobuf.Empty>(
                service, METHODID_DELETE_USER_IMAGE)))
        .build();
  }

  private static abstract class ImageAdminBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ImageAdminBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return fctreddit.impl.grpc.generated_java.ImageAdminProtoBuf.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ImageAdmin");
    }
  }

  private static final class ImageAdminFileDescriptorSupplier
      extends ImageAdminBaseDescriptorSupplier {
    ImageAdminFileDescriptorSupplier() {}
  }

  private static final class ImageAdminMethodDescriptorSupplier
      extends ImageAdminBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ImageAdminMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ImageAdminGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ImageAdminFileDescriptorSupplier())
              .addMethod(getDeleteUserImageMethod())
              .build();
        }
      }
    }
    return result;
  }
}
