package fctreddit.impl.grpc.generated_java;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.62.2)",
    comments = "Source: src/fctreddit/impl/api/grpc/ContentAdmin.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ContentAdminGrpc {

  private ContentAdminGrpc() {}

  public static final java.lang.String SERVICE_NAME = "ContentAdmin";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<fctreddit.impl.grpc.generated_java.ContentAdminProtoBuf.HandleDeletedUserArgs,
      com.google.protobuf.Empty> getHandleDeletedUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "handleDeletedUser",
      requestType = fctreddit.impl.grpc.generated_java.ContentAdminProtoBuf.HandleDeletedUserArgs.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fctreddit.impl.grpc.generated_java.ContentAdminProtoBuf.HandleDeletedUserArgs,
      com.google.protobuf.Empty> getHandleDeletedUserMethod() {
    io.grpc.MethodDescriptor<fctreddit.impl.grpc.generated_java.ContentAdminProtoBuf.HandleDeletedUserArgs, com.google.protobuf.Empty> getHandleDeletedUserMethod;
    if ((getHandleDeletedUserMethod = ContentAdminGrpc.getHandleDeletedUserMethod) == null) {
      synchronized (ContentAdminGrpc.class) {
        if ((getHandleDeletedUserMethod = ContentAdminGrpc.getHandleDeletedUserMethod) == null) {
          ContentAdminGrpc.getHandleDeletedUserMethod = getHandleDeletedUserMethod =
              io.grpc.MethodDescriptor.<fctreddit.impl.grpc.generated_java.ContentAdminProtoBuf.HandleDeletedUserArgs, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "handleDeletedUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fctreddit.impl.grpc.generated_java.ContentAdminProtoBuf.HandleDeletedUserArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new ContentAdminMethodDescriptorSupplier("handleDeletedUser"))
              .build();
        }
      }
    }
    return getHandleDeletedUserMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ContentAdminStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ContentAdminStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ContentAdminStub>() {
        @java.lang.Override
        public ContentAdminStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ContentAdminStub(channel, callOptions);
        }
      };
    return ContentAdminStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ContentAdminBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ContentAdminBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ContentAdminBlockingStub>() {
        @java.lang.Override
        public ContentAdminBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ContentAdminBlockingStub(channel, callOptions);
        }
      };
    return ContentAdminBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ContentAdminFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ContentAdminFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ContentAdminFutureStub>() {
        @java.lang.Override
        public ContentAdminFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ContentAdminFutureStub(channel, callOptions);
        }
      };
    return ContentAdminFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void handleDeletedUser(fctreddit.impl.grpc.generated_java.ContentAdminProtoBuf.HandleDeletedUserArgs request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getHandleDeletedUserMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ContentAdmin.
   */
  public static abstract class ContentAdminImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ContentAdminGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ContentAdmin.
   */
  public static final class ContentAdminStub
      extends io.grpc.stub.AbstractAsyncStub<ContentAdminStub> {
    private ContentAdminStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ContentAdminStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ContentAdminStub(channel, callOptions);
    }

    /**
     */
    public void handleDeletedUser(fctreddit.impl.grpc.generated_java.ContentAdminProtoBuf.HandleDeletedUserArgs request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getHandleDeletedUserMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ContentAdmin.
   */
  public static final class ContentAdminBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ContentAdminBlockingStub> {
    private ContentAdminBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ContentAdminBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ContentAdminBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.google.protobuf.Empty handleDeletedUser(fctreddit.impl.grpc.generated_java.ContentAdminProtoBuf.HandleDeletedUserArgs request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getHandleDeletedUserMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ContentAdmin.
   */
  public static final class ContentAdminFutureStub
      extends io.grpc.stub.AbstractFutureStub<ContentAdminFutureStub> {
    private ContentAdminFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ContentAdminFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ContentAdminFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> handleDeletedUser(
        fctreddit.impl.grpc.generated_java.ContentAdminProtoBuf.HandleDeletedUserArgs request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getHandleDeletedUserMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_HANDLE_DELETED_USER = 0;

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
        case METHODID_HANDLE_DELETED_USER:
          serviceImpl.handleDeletedUser((fctreddit.impl.grpc.generated_java.ContentAdminProtoBuf.HandleDeletedUserArgs) request,
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
          getHandleDeletedUserMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              fctreddit.impl.grpc.generated_java.ContentAdminProtoBuf.HandleDeletedUserArgs,
              com.google.protobuf.Empty>(
                service, METHODID_HANDLE_DELETED_USER)))
        .build();
  }

  private static abstract class ContentAdminBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ContentAdminBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return fctreddit.impl.grpc.generated_java.ContentAdminProtoBuf.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ContentAdmin");
    }
  }

  private static final class ContentAdminFileDescriptorSupplier
      extends ContentAdminBaseDescriptorSupplier {
    ContentAdminFileDescriptorSupplier() {}
  }

  private static final class ContentAdminMethodDescriptorSupplier
      extends ContentAdminBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ContentAdminMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (ContentAdminGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ContentAdminFileDescriptorSupplier())
              .addMethod(getHandleDeletedUserMethod())
              .build();
        }
      }
    }
    return result;
  }
}
